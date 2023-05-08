# Define variables
ANGULAR_CLI = ng
ANGULAR_SERVE_FLAGS = --open
ANGULAR_PROJECT_DIR = tables_web

# Define targets
serve:
	cd $(ANGULAR_PROJECT_DIR) && $(ANGULAR_CLI) serve $(ANGULAR_SERVE_FLAGS)

spring-boot:
	./gradlew build
	./gradlew bootRun