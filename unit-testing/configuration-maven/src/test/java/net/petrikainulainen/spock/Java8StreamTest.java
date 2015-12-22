package net.petrikainulainen.spock;
import java.util.OptionalDouble;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Collectors;
import org.junit.Test;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import java.util.stream.DoubleStream;
import org.apache.log4j.Logger;
public class Java8StreamTest {
	
	List<Item> items;
	List<Fruit> fruits;
    private Hello hello;
    private static final Logger logger = Logger
			.getLogger(Java8StreamTest.class);
    class Fruit {
    	String name;
    	
    	public Fruit(String name){
    		this.name=name;
    	}
    	public String getName(){
    		return name;
    	}
    }
    class Item {

		String key;

		public Item(String key) {
			super();
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}
    @Before
    public void createHelloObject() {
        hello = new Hello();
items = new ArrayList<>();
		
		items.add(new Item("ONE"));
		items.add(new Item("TWO"));
		items.add(new Item("THREE"));
		 fruits = Arrays.asList(new Fruit("apple"),
                new Fruit("grape"),
                new Fruit("orange"));
    }

    @Test
    public void sayHello_ShouldSayHelloToPersonWhoseNameIsGivenAsMethodParameter() {
        String greeting = hello.sayHello("Petri");
        assertThat(greeting, is("Hello Petri"));
    }
    
    @Test
    
    public void stramOfStringsToArray() {
    	String[] stringarray= Stream.of("one","two","three").toArray(String[]::new);
    	
    	assertTrue(stringarray.length==3);
    }
    @Test
    
    public void streamofInts() {
    	
    	//Integer[] intarray= Stream.of(1,2,3).toArray(Integer[]:new);
    	Integer[] stringArray = Stream.of(1, 2, 3).toArray(Integer[]::new);
    	assertTrue(stringArray.length==3);
    }
    @Test
    public void stramofsmallints() {
    	int[] arrray= IntStream.of(1,2,3).toArray();
    	assertTrue(arrray.length==3);
    }
    @Test
    public void longstream() {
    	long[] array= LongStream.of(1,2,3).toArray();
    	assertTrue(array.length==3);
    	
    }
   
    @Test
    public void convertiingstramtolist() {
    	
    	List<String> liststring= Stream.of("a","b","c").collect(toList());
    	assertTrue(liststring.size()==3);
    	
    }
    @Test
	public void stream_from_function() {

		Stream.iterate(0, n -> n + 3).limit(10).forEach(System.out::println);

	}
    @Test
    public void mapper(){
    	
    	
    	Map<String, Item> map = items.stream().collect(
				Collectors.toMap(Item::getKey, item -> item));
    	 map.forEach((k,v)->System.out.println("key:"+k +"  value:"+v));
    	 System.out.println(map);
    	 logger.info(map);
		assertTrue(map.keySet().size() == 3);
		
    }
    @Test
    public void streamtoset() {
    	
    	Set set=items.stream().collect(toSet());
    	assertTrue(set.size()==3);
    }
    
    @Test
    public void stramofstrings() {
    	
    String string=	Stream.of("abc","def").collect(Collectors.joining());
    assertEquals("abcdef" ,string);
    }
    @Test
    public void DouleStreamBuilder() {
    	
    	//Double sum = DoubleStream().builder().add(10).add(10).sum();
    	double sum = DoubleStream.builder().add(10).add(10).build().sum();
    	assertEquals(sum ,20,0);
    }
    @Test
    public void Doublestreamconcat() {
    	
    	//DoubleStream first= DoubleStream().builder().add(10).build();
    	//DoubleStream second = DoubleStream().builder().add(10).build();
    	//DoubleStream concat= DoubleStream().concat(first,second);
    	//assertEquals(20,concat.sum(),0);
    	DoubleStream first = DoubleStream.builder().add(10).build();
		DoubleStream second = DoubleStream.builder().add(10).build();

		DoubleStream third = DoubleStream.concat(first, second);

		assertEquals(20, third.sum(), 0);
    }
    
    
    @Test
    public void DoubleStreamTest() {
    	
    	DoubleStream empty = DoubleStream.empty();
    	assertTrue(empty.count()==0);
    }
    
    @Test
    public void getMaxofThestream() {
    	
    	OptionalDouble max= DoubleStream.of(5,10,15).max();
    	
    	assertEquals(15,max.getAsDouble(),0);
    }
    @Test
    
    public void parsetoDoubleStream() {
    	
    	List<String>doubles = new ArrayList<String>();
    	doubles.add("1");
    	doubles.add("2");
    	doubles.add("3");
    	OptionalDouble  doublenumber = doubles.stream().mapToDouble(Double::parseDouble).max();
    	assertEquals(3, doublenumber.getAsDouble(),0);
    }
    @Test 
    
    public void getTheLastElementOfthestream() {
    	Optional<String>value = Stream.of("a","b","c").reduce((a,b)->b);
    	assertEquals("c",value.get());
    	String lastValue = Stream.of("a", "b", "c").reduce((a, b) -> b)
				.orElse("false");

		assertEquals("c", lastValue);
    }
    public  Optional<Fruit> find(String name, List<Fruit> fruits) {
    	   for(Fruit fruit : fruits) {
    	      if(fruit.getName().equals(name)) {
    	         return Optional.of(fruit);
    	      }
    	   }
    	   return Optional.empty();
    	}
    @Test
    public void useOptionsal() {
    	Optional<Fruit> found = find("orange", fruits);	
    	 Fruit fruit = found.get();
    	//assertEquals("lemon" , fruit.getName(),0);
    	 assertEquals("orange" , fruit.getName());
    	 Optional<Fruit> found1 = find("loe", fruits);	
    	 assertEquals(found1,Optional.empty());
    }
    
    
}
