package org.hello.test;

import static org.junit.Assert.*;

import org.hello.HelloActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked") //忽略unchecked的warning
public class TestHelloActivity extends ActivityInstrumentationTestCase2{//public class TestHelloActivity extends ActivityInstrumentationTestCase2<要测试的类>

	private Solo solo;
	private static final String TARGET_PACKAGE_ID = "org.hello";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME="org.hello.HelloActivity";
	
	private static Class<?> launcherActivityClass;
	static{
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public TestHelloActivity() throws ClassNotFoundException
	{
		//super("要测试类的包名",要测试的类)
		//super("org.hello",HelloActivity.class);
		super(TARGET_PACKAGE_ID,launcherActivityClass);
	}
	
	@Before
	//junit初始化，每次执行测试方法，先执行setup
	public void setUp() throws Exception {
		super.setUp();
		//初始化solo,构造函数实例化
		this.solo = new Solo(getInstrumentation(), getActivity());
	}

	@After
	public void tearDown() throws Exception {
		try {
			this.solo.finalize();//执行清理
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getActivity().finish();//测试结束，关掉应用程序
		super.tearDown();
	}

	@Test
	//测试程序
	public void test() {
		assertTrue(this.solo.searchText("Hello world!"));
	}

}
