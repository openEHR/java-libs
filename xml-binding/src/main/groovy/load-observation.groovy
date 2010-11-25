import org.openehr.schemas.v1.*;
import org.openehr.binding.*;

input = new FileInputStream(new File("c:\\tmp\\original_version_002.xml"));

xml = VersionDocument.Factory.parse(input).getVersion()
ver = new XMLBinding().bindToRM(xml)
comp = ver.getData()
section = comp.getContent().get(0)
obser = section.getItems().get(0)

println 'name: ' + obser.getName() + ', nodeId: ' + obser.getArchetypeNodeId()