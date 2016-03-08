package com.wandrell.example.mule.swss.testing.unit.transformer;

import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.swss.flow.transformer.sample.SampleResponseToSampleTransformer;
import com.wandrell.example.mule.swss.model.sample.Sample;

public final class TestSampleResponseToSampleTransformer {

	private final String sourceCodeFirst;
	private final String sourceWSDLFirst;

	{
		sourceCodeFirst = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getSampleResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><cod1>1.0</cod1><cod2>2.1</cod2><description>desc</description><extra>extra</extra></return></ns2:getSampleResponse>";
		sourceWSDLFirst = "<com.wandrell.example.mule.swss.generated.samples.GetSampleResponse_-Return><cod1>1.0</cod1><cod2>2.1</cod2><description>desc</description><extra>extra</extra></com.wandrell.example.mule.swss.generated.samples.GetSampleResponse_-Return>";
	}

	public TestSampleResponseToSampleTransformer() {
		super();
	}

	@Test
	public final void testTransform_CodeFirst() throws TransformerException {
		final Sample sample;
		final Transformer transformer;

		transformer = new SampleResponseToSampleTransformer();

		sample = (Sample) transformer.transform(sourceCodeFirst);

		Assert.assertEquals(sample.getCod1(), new Float(1));
		Assert.assertEquals(sample.getCod2(), new Float(2.1));
		Assert.assertEquals(sample.getDescription(), "desc");
		Assert.assertEquals(sample.getExtra(), "extra");
	}

	@Test
	public final void testTransform_WSDLFirst() throws TransformerException {
		final Sample sample;
		final Transformer transformer;

		transformer = new SampleResponseToSampleTransformer();

		sample = (Sample) transformer.transform(sourceWSDLFirst);

		Assert.assertEquals(sample.getCod1(), new Float(1));
		Assert.assertEquals(sample.getCod2(), new Float(2.1));
		Assert.assertEquals(sample.getDescription(), "desc");
		Assert.assertEquals(sample.getExtra(), "extra");
	}

}
