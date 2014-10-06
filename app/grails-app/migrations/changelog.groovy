databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1412587548156-1") {
		createTable(tableName: "match") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "matchPK")
			}

			column(name: "version", type: "int8") {
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

	changeSet(author: "pivotal (generated)", id: "1412587548156-2") {
		createTable(tableName: "match_request") {
			column(name: "uuid", type: "varchar(255)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "match_requestPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "requester_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pivotal (generated)", id: "1412587548156-3") {
		createTable(tableName: "result") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "resultPK")
			}

			column(name: "version", type: "int8") {
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

	changeSet(author: "pivotal (generated)", id: "1412587548156-6") {
		createSequence(sequenceName: "hibernate_sequence")
	}

	changeSet(author: "pivotal (generated)", id: "1412587548156-4") {
		addForeignKeyConstraint(baseColumnNames: "match_request1_id", baseTableName: "match", constraintName: "FK_aavvm3veobpacpnqd8o3gr775", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}

	changeSet(author: "pivotal (generated)", id: "1412587548156-5") {
		addForeignKeyConstraint(baseColumnNames: "match_request2_id", baseTableName: "match", constraintName: "FK_lkbnrcv1u8ni17f5qr30b1ogs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "uuid", referencedTableName: "match_request", referencesUniqueColumn: "false")
	}

	include file: 'add-proposed-date-to-match.groovy'
}
