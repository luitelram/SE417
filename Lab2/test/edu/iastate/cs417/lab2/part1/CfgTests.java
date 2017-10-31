package edu.iastate.cs417.lab2.part1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import edu.iastate.cs417.lab2.util.FileUtil;

/**
 * 
 * CFG derived (Part I) tests go in this class. The tests
 *  should be simple unit tests. One test method per test case; all in
 *  this class.
 *  @author Ram Luitel
 *
 */
public class CfgTests {

	/*
	 * When file do not exist
	 */
	@Test
	public void testNoFileFound() {
		assertArrayEquals(null, FileUtil.getParametersFromFile("nofile", 2));
	}

	/*
	 * When there is proper file and it is found
	 */
	@Test
	public void testFileFound() {
		Object[][] fileFound = FileUtil.getParametersFromFile("./test1.txt", 3);
		Object[][] expected = { { "abcd", "efgh", "ijkl" }, { "mop", "ours", "uvwx" }, };
		assertArrayEquals(expected, fileFound);
	}

	/*
	 * Empty file but have header only
	 */
	@Test
	public void testEmptyFileFound() {
		Object[][] fileFound = FileUtil.getParametersFromFile("./emptyFile.txt", 0);
		assertEquals(0, fileFound.length);
	}
	/*
	 * Empty file no headers. Since the size of empty file is zero.
	 */
	@Test
	public void testEmptyFileNoHeaders() {
		Object [][] file = FileUtil.getParametersFromFile("./emptyFileNoHeader.txt", 0);
		assertEquals(0, file.length);
	}
	/*
	 *  files without header
	 *  This is defect
	 */
	@Test
	public void testFileMissingHeader(){
		Object[][] expected = { { "abcd", "efgh", "ijkl" }, { "mop", "ours", "uvwx" }, };
		assertArrayEquals(expected, FileUtil.getParametersFromFile("./test1.txt", 3));
	}
	/*
	 * When file have one of the rows with extra column.
	 * Example
	 * 	col1		col2	col3
	 * 	1			2			3
	 *  4			5			6	7
	 *  
	 *  This is the defect. See Defect documentation 
	 */
	@Test
	@Ignore
	public void testFileWithExtraCol(){
		Object[][] expected = { { "abcd", "efgh", "ijkl"}, { "mop", "ours", "uvwx" ,"extra"}, };
		assertArrayEquals(expected, FileUtil.getParametersFromFile("./ExtraColsFile.txt", 3));
	}
	/*
	 * Wrong number of column. When cols< actual number of cols
	 * MalformedTestSet
	 */
	@Test
	public void testWrongNumberOfCols(){
		assertArrayEquals(null, FileUtil.getParametersFromFile("./test1.txt", 4));
	}
}