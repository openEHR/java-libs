package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

public class MockEvaluator implements PathEvaluator {

	public List<String> getAllChildrenNames(ObjectValue current) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> getChildren(ObjectValue current, String childName) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getMetadata(ObjectValue current, String metadataName) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNodeName(ObjectValue current) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectValue getParent(ObjectValue current) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectValue getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isInstanceOf(ObjectValue current, String className) throws PathEvaluationException {
		// TODO Auto-generated method stub
		return false;
	}

}
