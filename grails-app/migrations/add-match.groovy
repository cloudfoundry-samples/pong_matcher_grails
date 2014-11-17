databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1413192408422-1") {
		createTable(tableName: "match") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "matchPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "match_request1_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "match_request2_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pivotal (generated)", id: "1413192408422-4") {
		createIndex(indexName: "FK_aavvm3veobpacpnqd8o3gr775", tableName: "match") {
			column(name: "match_request1_id")
		}
	}

	changeSet(author: "pivotal (generated)", id: "1413192408422-5") {
		createIndex(indexName: "FK_lkbnrcv1u8ni17f5qr30b1ogs", tableName: "match") {
			column(name: "match_request2_id")
		}
	}

	changeSet(author: "pivotal (generated)", id: "1413192408422-2") {
		addForeignKeyConstraint(baseColumnNames: "match_request1_id", baseTableName: "match", constraintName: "FK_aavvm3veobpacpnqd8o3gr775", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}

	changeSet(author: "pivotal (generated)", id: "1413192408422-3") {
		addForeignKeyConstraint(baseColumnNames: "match_request2_id", baseTableName: "match", constraintName: "FK_lkbnrcv1u8ni17f5qr30b1ogs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}
}
