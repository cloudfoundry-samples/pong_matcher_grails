databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1413192535904-1") {
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
}
