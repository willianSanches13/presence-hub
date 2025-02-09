import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    baseUrl: 'http://localhost:4200',
    supportFile: false,
    specPattern: 'cypress/**/*.spec.ts',
    excludeSpecPattern: ['browserlistsrc'],
  },
  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
    },
    specPattern: "**/*.cy.ts",
    excludeSpecPattern: ['browserlistsrc'],
  },
});
