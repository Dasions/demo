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
		//1 ģ��һ��list����
		List<String> mockedList = mock(List.class);
		//2 using mock object
		 mockedList.add("one");
		 mockedList.clear();

		 //3 verification ��֤�Ƿ�ִ����2�еĲ���
		 verify(mockedList).add("one");
		 verify(mockedList).clear();
	}
	
	@Test
	public void UsingWhenAndVerify(){
		LinkedList mockedList = mock(LinkedList.class);

		/**
		 * ����0,1��Ӧ��ֵ��������mockedList.get(i)ʱ��
		 * ����i��ֵ��thenReturn���õ�ֵ����Ԥ���ֵ
		 */
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());
		 
		 //���first
		 System.out.println(mockedList.get(0));
		 
		 //�׳��쳣
		 //System.out.println(mockedList.get(1));
		 
		 //���null,��Ϊǰ��û��Ԥ���Ӧ������ֵ
		 System.out.println(mockedList.get(999));
		 
		 verify(mockedList).get(0);
	}
	
	@Test
	public void UsingArgumentAndMatchers(){
		LinkedList mockedList = mock(LinkedList.class);
		
		/**
		 * ��������������ֵ��������mockedList.get(i)ʱ��
		 * ����i������ֵ���������element
		 */
		when(mockedList.get(anyInt())).thenReturn("element");
		
		System.out.println(mockedList.get(9));
		System.out.println(mockedList.get(99));
		
		Map  map= mock(HashMap.class);
		/**
		 * ��������key��ֵ��������map.get(key)ʱ��
		 * ����key������ֵ���������string element
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

		 //������������Ч����һ���ģ�����У�� �Ƿ�ִ����һ��mockedList.add("once")
		 verify(mockedList).add("once");
		 verify(mockedList, times(1)).add("once");

		 //У���Ƿ����������mockedList.add("twice")������mockedList.add("three times")
		 verify(mockedList, times(2)).add("twice");
		 verify(mockedList, times(3)).add("three times");

		 //У���Ƿ��δִ�й� mockedList.add("never happened")
		 verify(mockedList, never()).add("never happened");

		 //У������ִ�й����δ���
		 verify(mockedList, atLeastOnce()).add("three times");
		 verify(mockedList, atLeast(2)).add("three times");
		 verify(mockedList, atMost(5)).add("three times");
	}
	
	@Test
	public void UsingDoThrow(){
		LinkedList<String> mockedList = mock(LinkedList.class);
		//������clear()ʱ��������ʱ�쳣
		doThrow(new RuntimeException()).when(mockedList).clear();
		mockedList.clear();
		
		//������add("throw")ʱ�׳�����ʱ�쳣
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

		 //��Ե���mock
		 //create an inOrder verifier for a single mock
		 InOrder inOrder = inOrder(singleMock);

		 //У���һ��������� "was added first",�ڶ���������� "was added second"
		 //following will make sure that add is first called with "was added first, then with "was added second"
		 inOrder.verify(singleMock).add("was added first");
		 inOrder.verify(singleMock).add("was added second");

		 // B. Multiple mocks that must be used in a particular order
		 List firstMock = mock(List.class);
		 List secondMock = mock(List.class);

		 //��Զ��mock
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
		//У�� mockOne�Ƿ񱻲�����������add�����ǰ�����verify(mockOne).add("one");�������У���ʧЧ
		verifyZeroInteractions(mockOne);
		System.out.println("1");
	}
	
	@Test
	public void usingWhenAndReturns(){
		List mock = mock(List.class);
		 when(mock.size())
		   .thenReturn(1)
		   .thenReturn(2);

		 //��һ�ε������1
		 System.out.println(mock.size());

		 //�ڶ������ 2
		 System.out.println(mock.size());

		 //���������2���������mock.size()��Ȼ���2
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
