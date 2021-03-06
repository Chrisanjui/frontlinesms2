package frontlinesms2.settings

import frontlinesms2.*

import spock.lang.*

class ChangeLanguageSpec extends grails.plugin.geb.GebSpec {
	def 'language list should be available on the settings page and should be sorted alphabetically'() {
		when:
			to PageGeneralSettings
		then:
			languageList.children()*.text()== ["English", "Arabic", "Deutsch", "English", "Español", "French", "Indonesian", 'Japanese', "Kiswahili", "Português", "Русский"]
	}

	def 'Can change language of the application'() {
		given:
			to PageGeneralSettings
			waitFor { title.contains('Settings') }
		when:
			setLanguage('Kiswahili')
		then:
			waitFor { title.contains('Mazingira') }
		cleanup:
			setLanguage('English')
			waitFor { title.contains('Settings') }
	}
}

