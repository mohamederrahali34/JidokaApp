MIGRATION_LABEL = to-be-changed
DATE_WITH_TIME := $(shell /bin/date -u "+%Y%m%d-%H%M%S")
DIFF_CHANGELOG_FILE := src/main/resources/db/changelog/${DATE_WITH_TIME}-${MIGRATION_LABEL}.yaml
CHANGE_LOG_MASTER := src/main/resources/db/changelog-master.yaml

makeMigration:
	mvnw clean compile
	mvnw liquibase:diff "-Dliquibase.diffChangeLogFile=${DIFF_CHANGELOG_FILE}"
	@echo "  - include:" >> "${CHANGE_LOG_MASTER}"
	@echo "      file: ${DIFF_CHANGELOG_FILE}" >> "${CHANGE_LOG_MASTER}"

applyMigrations:
	mvnw liquibase:update
