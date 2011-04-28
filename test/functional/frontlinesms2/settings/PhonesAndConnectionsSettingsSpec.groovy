package frontlinesms2.settings

import frontlinesms2.*
import frontlinesms2.connection.ConnectionListPage

class PhonesAndConnectionsSettingsSpec extends grails.plugin.geb.GebSpec {
	
	def 'add new connection option is available in connection settings panel'() {
		when:
			to ConnectionListPage
		then:
			btnNewConnection.text() == "Add new connection"
			assert btnNewConnection.children().getAttribute("href") == "/frontlinesms2/connection/create"
	}
	

	def 'connections are listed in "phone & connections" panel'() {
		given:
			createTestConnections()
		when:
			to ConnectionListPage
		then:
			println "Connections: ${Fconnection.findAll()}"
			lstConnections != null
			lstConnections.find('h2')*.text() == ['MTN Dongle', 'David\'s Clickatell account', 'Miriam\'s Clickatell account']
		cleanup:	
			deleteTestConnections()
	}

	def createTestConnections() {
		[new Fconnection(name:'MTN Dongle', type:'Phone/Modem', camelAddress:'1'),
				new Fconnection(name:'David\'s Clickatell account', type:'Clickatell SMS Gateway', camelAddress:'2'),
				new Fconnection(name:'Miriam\'s Clickatell account', type:'Clickatell SMS Gateway', camelAddress:'3')].each() {
			it.save(flush:true, failOnError: true)
		}
	}

	def deleteTestConnections() {
		Fconnection.findAll().each() { it.delete(flush: true) }
	}
}


