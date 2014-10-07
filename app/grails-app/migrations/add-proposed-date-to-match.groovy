databaseChangeLog = {

	changeSet(author: "pivotal (generated)", id: "1412700272576-1") {
		addColumn(tableName: "match") {
			column(name: "proposed_date", type: "datetime")
		}
	}
}
