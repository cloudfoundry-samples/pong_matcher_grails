databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1412697727130-1") {
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

			column(name: "proposed_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-2") {
		createTable(tableName: "match_request") {
			column(name: "uuid", type: "varchar(255)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "match_requestPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "requester_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-3") {
		createTable(tableName: "result") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "resultPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "loser_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "winner_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-6") {
		createIndex(indexName: "FK_aavvm3veobpacpnqd8o3gr775", tableName: "match") {
			column(name: "match_request1_id")
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-7") {
		createIndex(indexName: "FK_lkbnrcv1u8ni17f5qr30b1ogs", tableName: "match") {
			column(name: "match_request2_id")
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-4") {
		addForeignKeyConstraint(baseColumnNames: "match_request1_id", baseTableName: "match", constraintName: "FK_aavvm3veobpacpnqd8o3gr775", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}

	changeSet(author: "pivotal (generated)", id: "1412697727130-5") {
		addForeignKeyConstraint(baseColumnNames: "match_request2_id", baseTableName: "match", constraintName: "FK_lkbnrcv1u8ni17f5qr30b1ogs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}
}
