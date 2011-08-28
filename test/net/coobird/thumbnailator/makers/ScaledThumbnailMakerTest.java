package net.coobird.thumbnailator.makers;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;

import net.coobird.thumbnailator.builders.BufferedImageBuilder;

import org.junit.Test;

/**
 * A class which tests the behavior of the 
 * {@link ScaledThumbnailMaker} class.
 * 
 * @author coobird
 *
 */
public class ScaledThumbnailMakerTest
{
	/**
	 * A convenience method to make a test image.
	 */
	private static BufferedImage makeTestImage200x200() {
		return new BufferedImageBuilder(200, 200).build();
	}

	/**
	 * Test for the {@link ScaledThumbnailMaker} class where,
	 * <ol>
	 * <li>It is initialized with the no-args constructor</li>
	 * <li>The scale method is not called</li>
	 * <li>And finally the make method is called</li>
	 * </ol>
	 * and the expected outcome is,
	 * <ol>
	 * <li>An IllegalStateException occurs.</li>
	 * </ol>
	 */
	@Test(expected=IllegalStateException.class)
	public void uninitializedWithNoArgConstructor()
	{
		// given
		BufferedImage img = makeTestImage200x200();
		
		// when
		new ScaledThumbnailMaker().make(img);
		
		// then
		fail();
	}
	
	/**
	 * Test for the {@link FixedSizeThumbnailMaker} class where,
	 * <ol>
	 * <li>It is initialized with the one argument constructor</li>
	 * <li>And finally the make method is called</li>
	 * </ol>
	 * and the expected outcome is,
	 * <ol>
	 * <li>A thumbnail image is created by reducing the size by the
	 * specified scaling factor</li>
	 * <li>The imageType is the default type.</li>
	 * </ol>
	 */
	@Test
	public void makeWithOneArgConstructor()
	{
		BufferedImage img = makeTestImage200x200();
		
		BufferedImage thumbnail = new ScaledThumbnailMaker(0.5).make(img);
		
		assertEquals(100, thumbnail.getWidth());
		assertEquals(100, thumbnail.getHeight());
		assertEquals(BufferedImage.TYPE_INT_ARGB, thumbnail.getType());
	}
	
	/**
	 * Test for the {@link FixedSizeThumbnailMaker} class where,
	 * <ol>
	 * <li>It is initialized with the one argument constructor</li>
	 * <li>The scale method is called.</li>
	 * <li>And finally the make method is called</li>
	 * </ol>
	 * and the expected outcome is,
	 * <ol>
	 * <li>An IllegalStateException is thrown, due to the scaling factor
	 * being specified twice.</li>
	 * </ol>
	 */
	@Test(expected=IllegalStateException.class)
	public void makeWithOneArgConstructorWithScaleOneArg()
	{
		BufferedImage img = makeTestImage200x200();
		
		new ScaledThumbnailMaker(0.5)
			.scale(0.5)
			.make(img);
	}
	
	@Test(expected=IllegalStateException.class)
	public void makeWithOneArgConstructorWithScaleTwoArg()
	{
		BufferedImage img = makeTestImage200x200();
		
		new ScaledThumbnailMaker(0.5)
			.scale(0.5, 0.5)
			.make(img);
	}
	
	@Test
	public void makeWithTwoArgConstructor()
	{
		// given
		BufferedImage img = makeTestImage200x200();
		
		// when
		BufferedImage thumbnail = new ScaledThumbnailMaker(0.6, 0.4).make(img);
		
		// then
		assertEquals(120, thumbnail.getWidth());
		assertEquals(80, thumbnail.getHeight());
	}
	
	@Test(expected=IllegalStateException.class)
	public void makeWithTwoArgConstructorWithScaleOneArg()
	{
		// given
		BufferedImage img = makeTestImage200x200();
		
		// when
		new ScaledThumbnailMaker(0.6, 0.4)
			.scale(0.5)
			.make(img);
		
		// then
		fail();
	}
	
	@Test(expected=IllegalStateException.class)
	public void makeWithTwoArgConstructorWithScaleTwoArg()
	{
		// given
		BufferedImage img = makeTestImage200x200();
		
		// when
		new ScaledThumbnailMaker(0.6, 0.4)
			.scale(0.5, 0.5)
			.make(img);
		
		// then
		fail();
	}

	/**
	 * Test for the {@link FixedSizeThumbnailMaker} class where,
	 * <ol>
	 * <li>It is initialized with the no argument constructor</li>
	 * <li>The scale method is called.</li>
	 * <li>And finally the make method is called</li>
	 * </ol>
	 * and the expected outcome is,
	 * <ol>
	 * <li>A thumbnail image is created by reducing the size by the
	 * specified scaling factor</li>
	 * <li>The imageType is the default type.</li>
	 * </ol>
	 */
	@Test
	public void makeWithNoArgConstructorAndScaleOneArg()
	{
		BufferedImage img = makeTestImage200x200();
		
		BufferedImage thumbnail = new ScaledThumbnailMaker()
				.scale(0.5)
				.make(img);
		
		assertEquals(100, thumbnail.getWidth());
		assertEquals(100, thumbnail.getHeight());
		assertEquals(BufferedImage.TYPE_INT_ARGB, thumbnail.getType());
	}
	
	@Test
	public void makeWithNoArgConstructorAndScaleTwoArg()
	{
		// given
		BufferedImage img = makeTestImage200x200();
		
		// when
		BufferedImage thumbnail = new ScaledThumbnailMaker()
			.scale(0.6, 0.4)
			.make(img);
		
		// then
		assertEquals(120, thumbnail.getWidth());
		assertEquals(80, thumbnail.getHeight());
		assertEquals(BufferedImage.TYPE_INT_ARGB, thumbnail.getType());
	}
}
