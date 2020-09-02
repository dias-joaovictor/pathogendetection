package org.nal.pathogendetection.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.nal.pathogendetection.model.FilePathogenDetected;
import org.nal.pathogendetection.model.PathogenValue;

@RunWith(JUnit4.class)
public class PathogenDetectorGeneratorServiceTest {

	@Test
	public void testOK() {
		final File testFile = new File(this.getClass().getResource("/modelfile.csv").getFile());
		final List<FilePathogenDetected> filesDetected = PathogenDetectorGeneratorService.getPathogenDetected(testFile);

		assertEquals(3, filesDetected.size());
		FilePathogenDetected file = filesDetected.get(0);
		Iterator<PathogenValue> iterator = file.getAllValues().iterator();
		assertEquals("SAMN03216762", file.getFileName());
		PathogenValue pathogenValue = iterator.next();
		assertEquals("blaOXA-193", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("gyrA_T86I", pathogenValue.getColumn());
		assertTrue(!pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("tet(O)", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		file = filesDetected.get(1);
		iterator = file.getAllValues().iterator();
		assertEquals("SAMN03216759", file.getFileName());
		pathogenValue = iterator.next();
		assertEquals("blaOXA-193", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("gyrA_T86I", pathogenValue.getColumn());
		assertTrue(!pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("tet(O)", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		file = filesDetected.get(2);
		iterator = file.getAllValues().iterator();
		assertEquals("SAMN04158379", file.getFileName());
		pathogenValue = iterator.next();
		assertEquals("blaOXA-193", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("gyrA_T86I", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

		pathogenValue = iterator.next();
		assertEquals("tet(O)", pathogenValue.getColumn());
		assertTrue(pathogenValue.isValue());

	}

}
