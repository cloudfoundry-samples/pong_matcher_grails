databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1413192035645-1") {
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
}
