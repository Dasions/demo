package mockitodemo;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

//http://static.javadoc.io/org.mockito/mockito-core/2.8.47/org/mockito/Mockito.html
public class MockitoDemo {

	@Test
	public void UsingMockAndVerify(){
		//1 模拟一个list对象
		List<String> mockedList = mock(List.class);
		//2 using mock object
		 mockedList.add("one");
		 mockedList.clear();

		 //3 verification 验证是否执行了2中的步骤
		 verify(mockedList).add("one");
		 verify(mockedList).clear();
	}
	
	@Test
	public void UsingWhenAndVerify(){
		LinkedList mockedList = mock(LinkedList.class);

		/**
		 * 设置0,1对应的值，当调用mockedList.get(i)时，
		 * 根据i的值和thenReturn设置的值返回预设的值
		 */
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());
		 
		 //输出first
		 System.out.println(mockedList.get(0));
		 
		 //抛出异常
		 //System.out.println(mockedList.get(1));
		 
		 //输出null,因为前面没有预设对应索引的值
		 System.out.println(mockedList.get(999));
		 
		 verify(mockedList).get(0);
	}
	
	@Test
	public void UsingArgumentAndMatchers(){
		LinkedList mockedList = mock(LinkedList.class);
		
		/**
		 * 设置任意索引的值，当调用mockedList.get(i)时，
		 * 无论i是上面值，都会输出element
		 */
		when(mockedList.get(anyInt())).thenReturn("element");
		
		System.out.println(mockedList.get(9));
		System.out.println(mockedList.get(99));
		
		Map  map= mock(HashMap.class);
		/**
		 * 设置任意key的值，当调用map.get(key)时，
		 * 无论key是上面值，都会输出string element
		 */
		when(map.get(anyString())).thenReturn("string element");
		
		System.out.println(map.get("one"));
		System.out.println(map.get("two"));
	}
	
	@Test
	public void UsingVerify(){
		LinkedList<String> mockedList = mock(LinkedList.class);
		
		 //using mock
		 mockedList.add("once");

		 mockedList.add("twice");
		 mockedList.add("twice");

		 mockedList.add("three times");
		 mockedList.add("three times");
		 mockedList.add("three times");

		 //下面两句代码的效果是一样的，都是校验 是否执行了一次mockedList.add("once")
		 verify(mockedList).add("once");
		 verify(mockedList, times(1)).add("once");

		 //校验是否调用了两次mockedList.add("twice")和三次mockedList.add("three times")
		 verify(mockedList, times(2)).add("twice");
		 verify(mockedList, times(3)).add("three times");

		 //校验是否从未执行过 mockedList.add("never happened")
		 verify(mockedList, never()).add("never happened");

		 //校验至少执行过几次代码
		 verify(mockedList, atLeastOnce()).add("three times");
		 verify(mockedList, atLeast(2)).add("three times");
		 verify(mockedList, atMost(5)).add("three times");
	}
	
	@Test
	public void UsingDoThrow(){
		LinkedList<String> mockedList = mock(LinkedList.class);
		//当调用clear()时，抛运行时异常
		doThrow(new RuntimeException()).when(mockedList).clear();
		mockedList.clear();
		
		//当调用add("throw")时抛出运行时异常
		doThrow(new RuntimeException()).when(mockedList).add("throw");
		mockedList.add("onece");
		System.out.println("add onece");
		mockedList.add("throw");
		System.out.println("add throw");
	}
	
	@Test
	public void UsingVerifyInOrder(){
		 // A. Single mock whose methods must be invoked in a particular order
		 List singleMock = mock(List.class);

		 //using a single mock
		 singleMock.add("was added first");
		 singleMock.add("was added second");

		 //针对单个mock
		 //create an inOrder verifier for a single mock
		 InOrder inOrder = inOrder(singleMock);

		 //校验第一个加入的是 "was added first",第二个加入的是 "was added second"
		 //following will make sure that add is first called with "was added first, then with "was added second"
		 inOrder.verify(singleMock).add("was added first");
		 inOrder.verify(singleMock).add("was added second");

		 // B. Multiple mocks that must be used in a particular order
		 List firstMock = mock(List.class);
		 List secondMock = mock(List.class);

		 //针对多个mock
		 //using mocks
		 firstMock.add("was called first");
		 secondMock.add("was called second");

		 //create inOrder object passing any mocks that need to be verified in order
		 InOrder inOrdert = inOrder(firstMock, secondMock);

		 //following will make sure that firstMock was called before secondMock
		 inOrdert.verify(firstMock).add("was called first");
		 inOrdert.verify(secondMock).add("was called second");
	}
	
	
	@Test
	public void usingInteractionsVerify(){
		List mockOne = mock(List.class);
		List mockTwo = mock(List.class);
		List mockThree = mock(List.class);

		mockOne.add("one");
		
		verify(mockOne, never()).add("two");
		//verify that other mocks were not interacted
		verifyZeroInteractions(mockTwo);
		System.out.println("2");
		verifyZeroInteractions(mockThree);
		System.out.println("3");
		//校验 mockOne是否被操作过，包括add；如果前面出现verify(mockOne).add("one");则下面的校验会失效
		verifyZeroInteractions(mockOne);
		System.out.println("1");
	}
	
	@Test
	public void usingWhenAndReturns(){
		List mock = mock(List.class);
		 when(mock.size())
		   .thenReturn(1)
		   .thenReturn(2);

		 //第一次调用输出1
		 System.out.println(mock.size());

		 //第二次输出 2
		 System.out.println(mock.size());

		 //第三次输出2，后面调用mock.size()仍然输出2
		 System.out.println(mock.size());
	}
	
	@Test
	public void usingCallbacks(){
		List mock = mock(List.class);
		when(mock.add(anyString())).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				 Object[] args = invocation.getArguments();
				 System.out.println(args[0]);
		         Object mock = invocation.getMock();
		         return true;
			}
		 });
		 System.out.println(mock.add("foo"));
	}
	
}
