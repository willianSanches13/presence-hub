provider "google" {
  project = "presence-hub" # Substitua pelo ID do seu projeto GCP
  region  = "us-central1"
}

resource "google_compute_instance" "java_api_vm" {
  name         = "java-api-vm"
  machine_type = "e2-micro" # Configure a máquina com recursos suficientes para sua aplicação
  zone         = "us-central1-a"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11" # Pode ajustar a imagem se necessário
    }
  }

  network_interface {
    network = "default"
    access_config {} # Permite um IP público para acesso externo
  }

  metadata = {
    ssh-keys = "root:${file("~/.ssh/id_rsa.pub")}" # Configura acesso SSH (ajuste o caminho da sua chave pública)
  }

  tags = ["http-server"]
}

resource "google_compute_firewall" "allow_http_8080" {
  name    = "allow-http-8080"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["8080"]
  }

  source_ranges = ["0.0.0.0/0"] # Permite acesso externo de qualquer IP
  target_tags   = ["http-server"]
}
