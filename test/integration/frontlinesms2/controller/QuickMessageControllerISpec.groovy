package frontlinesms2.controller

import spock.lang.*
import frontlinesms2.*
import grails.plugin.spock.*

class QuickMessageControllerISpec extends IntegrationSpec {
	def controller
	
	def setup() {
		controller = new QuickMessageController()
	}
	
	def "contact list returned should be sorted alphabetically"() {
		given:
			def contact2 = new Contact(name:'Charlie').save(failOnError:true, flush:true)
			def contact3 = new Contact(name:'Alice').save(failOnError:true, flush:true)
		when:
			def model = controller.create()
		then:
			model.contactList == [contact3, contact2]
	}
}