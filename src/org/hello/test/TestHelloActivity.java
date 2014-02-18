package org.hello.test;

import static org.junit.Assert.*;

import org.hello.HelloActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked") //����unchecked��warning
public class TestHelloActivity extends ActivityInstrumentationTestCase2{//public class TestHelloActivity extends ActivityInstrumentationTestCase2<Ҫ���Ե���>

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
		//super("Ҫ������İ���",Ҫ���Ե���)
		//super("org.hello",HelloActivity.class);
		super(TARGET_PACKAGE_ID,launcherActivityClass);
	}
	
	@Before
	//junit��ʼ����ÿ��ִ�в��Է�������ִ��setup
	public void setUp() throws Exception {
		super.setUp();
		//��ʼ��solo,���캯��ʵ����
		this.solo = new Solo(getInstrumentation(), getActivity());
	}

	@After
	public void tearDown() throws Exception {
		try {
			this.solo.finalize();//ִ������
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getActivity().finish();//���Խ������ص�Ӧ�ó���
		super.tearDown();
	}

	@Test
	//���Գ���
	public void test() {
		assertTrue(this.solo.searchText("Hello world!"));
	}

}
