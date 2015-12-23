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
import static com.google.common.truth.Truth.assertThat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

import org.paumard.model.McDonald;
public class Java8StreamTest {
	
	List<Item> items;
	List<Fruit> fruits;
    private Hello hello;
    private static final Logger logger = Logger
			.getLogger(Java8StreamTest.class);
    class Fruit {
    	String name;
    	private double level;
    	
    	public Fruit(String name,double level){
    		this.name=name;
    		this.level=level;
    	}
    	public String getName(){
    		return name;
    	}
    	public double  getLevel(){
    		return level;
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
		 fruits = Arrays.asList(new Fruit("apple",102),
                new Fruit("grape",102),
                new Fruit("orange",103));
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
    @Test
    
    public void useGroupingslevels() {
    	Map<Double, List<Fruit>> groupByLevel = fruits.stream()
				.collect(Collectors.groupingBy(Fruit::getLevel));

    	//Map<Double,Long>levels = fruits.stream().collect(Collectors.groupingBy(Fruit::getLevel));
    	assertEquals(2.0,groupByLevel.get(102.0).size(),0);
    	
    }
    @Test
    
    public void useGroupingsbyCounting() {
    	
    	//Map<Double, Long>groupsbycounting = fruits.stream().collect(Fruit::getLevel, Collectors.counting());
    	Map<Double, Long> groupByLevel = fruits.stream().collect(
				Collectors.groupingBy(Fruit::getLevel,
						Collectors.counting()));
    	assertEquals(2.0, groupByLevel.get(102.0), 0);
    }
    @Test 
    public void findElementLessThan3() {
    	long element = Stream.of(1,2,3,4,5).filter(p-> p.intValue()<4).count();
    	assertEquals(3,element);
    }
    @Test 
    public void applymaptostream() {
    	List<String> strings = Stream.of("one", null, "three").map(s -> {
			if (s == null)
				return "[unknown]";
			else
				return s;
		}).collect(Collectors.toList());
    	
    }
  @Test
  
  public void testmacd() throws Exception{
	  Stream<String> lines = Files.lines(Paths.get("files", "mcdonalds.csv")) ;
      List<McDonald> mcdos = lines.map(s -> {
          // -149.95038,61.13712,"McDonalds-Anchorage,AK","3828 W Dimond Blvd, Anchorage,AK, (907) 248-0597"
          // -72.84817,41.27988,"McDonalds-Branford,CT","424 W Main St, Branford,CT, (203) 488-9353"
          String [] strings = s.split(",") ;
          McDonald mdo = new McDonald() ;
          mdo.setLatitude(Double.parseDouble(strings[0])) ;
          mdo.setLongitude(Double.parseDouble(strings[1])) ;
          mdo.setName(strings[2].substring(1) + strings[3].substring(0, strings[3].length() - 1)) ;
          mdo.setAddress(strings[4].substring(1)) ;
          mdo.setCity(strings[5].trim()) ;
          mdo.setState(strings[6].trim()) ;
          if (mdo.state().endsWith("\"")) {
              mdo.setState(mdo.state().substring(0, mdo.state().length() - 1)) ;
          }
          if (mdo.state().contains(" ")) {
              mdo.setState(mdo.state().substring(0, mdo.state().indexOf(" "))) ;
          }
          if (mdo.state().length() > 2) {
              mdo.setState(strings[7].trim()) ;
          }
          return mdo ;
      }).collect(Collectors.toList()) ;
      
	  assertEquals(13912,mcdos.size());
	  System.out.println("# of McDos = " + mcdos.size()) ;
	  // The number of cities that have a McDonald
      long nTowns = 
      mcdos.stream()
              .map(McDonald::city)
              .collect(Collectors.toSet())
              .size() ;
      assertEquals(4927,nTowns);
      System.out.println("The number of cities that have a McDonald : " + nTowns) ;
      // The city has the most MacDonald
      Map.Entry<String, Long> entry = 
      mcdos.stream()
              .collect(
                      Collectors.groupingBy(
                              McDonald::city, 
                              Collectors.counting()
                      )
              )
              .entrySet()
              .stream()
              .max(Map.Entry.comparingByValue())
              .get() ;
      System.out.println("The city has the most MacDonald : " + entry) ;
  }

	
    
}
