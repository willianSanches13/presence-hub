provider "google" {
  project = var.project_id
  region  = var.region
}

# Variáveis
variable "project_id" {}
variable "region" {
  default = "us-east1"
}

# Criar um bucket do Google Cloud Storage para o frontend
resource "google_storage_bucket" "frontend_bucket" {
  name     = "${var.project_id}-frontend"
  location = var.region
}

# Firewall para permitir o tráfego HTTP e HTTPS
resource "google_compute_firewall" "default" {
  name    = "allow-http-https"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["80", "443"]
  }

  source_ranges = ["0.0.0.0/0"]
}

# Instância de teste do backend no Compute Engine
resource "google_compute_instance" "test_instance" {
  name         = "test-backend-instance"
  machine_type = "e2-micro"
  zone         = "us-east1-d"

  boot_disk {
    initialize_params {
      image = "gcr.io/${var.project_id}/backend-image"
    }
  }

  network_interface {
    network = "default"
    access_config {}
  }
}

# Instância de produção do backend no Compute Engine (com autoscaler)
resource "google_compute_instance_template" "prod_instance_template" {
  name         = "prod-instance-template"
  machine_type = "e2-medium"

  disk {
    boot = true
    initialize_params {
      image = "gcr.io/${var.project_id}/backend-image"
    }
  }

  network_interface {
    network = "default"
    access_config {}
  }
}

resource "google_compute_instance_group_manager" "prod_instance_group" {
  name               = "prod-instance-group"
  base_instance_name = "prod-instance"
  instance_template  = google_compute_instance_template.prod_instance_template.id
  zone               = "us-central1-f"
  target_size        = 1

  autoscaler {
    max_replicas = 3
    min_replicas = 1
    cpu_utilization {
      target = 0.6
    }
  }
}

# Cloud Load Balancer
resource "google_compute_backend_service" "backend_service" {
  name        = "backend-service"
  port_name   = "http"
  protocol    = "HTTP"
  timeout_sec = 10

  backend {
    group = google_compute_instance_group_manager.prod_instance_group.instance_group
  }
}

resource "google_compute_url_map" "url_map" {
  name            = "url-map"
  default_service = google_compute_backend_service.backend_service.self_link
}

resource "google_compute_target_http_proxy" "http_proxy" {
  name   = "http-lb-proxy"
  url_map = google_compute_url_map.url_map.self_link
}

resource "google_compute_global_forwarding_rule" "http_rule" {
  name       = "http-rule"
  ip_address = google_compute_global_address.default.address
  ip_protocol = "TCP"
  port_range = "80"
  target     = google_compute_target_http_proxy.http_proxy.self_link
}