import org.openehr.ehrdemo.*

service = new EhrServiceTester()

uid = '1004'

composition = service.retrieveCompositionById(uid)

println service.toXML(composition)