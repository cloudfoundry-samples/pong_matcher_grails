databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1412588751814-1") {
		addColumn(tableName: "match") {
			column(name: "proposed_date", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}
}
