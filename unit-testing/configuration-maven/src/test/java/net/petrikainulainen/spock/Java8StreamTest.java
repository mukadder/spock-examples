package net.petrikainulainen.spock;
import java.util.OptionalDouble;
import org.junit.Before;
import java.util.Comparator;
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
import java.util.IntSummaryStatistics;
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
import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
import java.util.function.Consumer;
import org.paumard.model.McDonald;
import org.paumard.model.Student;
import org.paumard.model.PrimeMinister;
import org.paumard.model.Person;
import org.paumard.model.Country;
import java.util.function.Function;
import org.paumard.model.Student1;
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
 /* We have a list of Student class. Grouping is done on the basis of student class name.
  List is converted into stream of student object. 
  Then call collect method of stream. groupingBy of Collectors class checks each element of stream 
  and gets class name and then group it as list. Finally we get a map where key is the
  one by which grouping is done. Find the complete example. */
	@Test
	
public void modelgroupingby(){
		Student s1 = new Student("Ram", "A", 20);
        Student s2 = new Student("Shyam", "B", 22);
        Student s3 = new Student("Mohan", "A", 22);
        Student s4 = new Student("Mahesh", "C", 20);
        Student s5 = new Student("Krishna", "B", 21);
        List<Student> list = Arrays.asList(s1,s2,s3,s4,s5);	
        System.out.println("----Group Student on the basis of ClassName----");
        Map<String, List<Student>> stdByClass = list.stream()
                    .collect(Collectors.groupingBy(Student::getClassName));
        stdByClass.forEach((k,v)->System.out.println("Key:"+k+"  "+ 
                ((List<Student>)v).stream().map(m->m.getName()).collect(Collectors.joining(","))));
      //Group Student on the basis of age
        System.out.println("----Group Student on the basis of age----");
        Map<Integer, List<Student>> stdByAge = list.stream()
                    .collect(Collectors.groupingBy(Student::getAge));
        
        stdByAge.forEach((k,v)->System.out.println("Key:"+k+"  "+ 
                ((List<Student>)v).stream().map(m->m.getName()).collect(Collectors.joining(","))));
	}
	@Test
	public void optiontest() {
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister());
        String pmName = pm.map(PrimeMinister::getName).orElse("None");
        System.out.println(pmName);
        //Assign Some Value to PrimeMinister.name
        pm = Optional.of(new PrimeMinister("Narendra Modi"));
        pmName = pm.map(PrimeMinister::getName).orElse("None");
        System.out.println(pmName);
	}
	
	@Test
	
	public void flatmapDemo() {
		Optional<PrimeMinister> primeMinister = Optional.of(new PrimeMinister("Narendra Modi"));
        Optional<Country> country = Optional.of(new Country(primeMinister));
        Optional<Person> person = Optional.of(new Person(country));
        String pmName= person.flatMap(Person::getCountry).flatMap(Country::getPrimeMinister)
                .map(PrimeMinister::getName).orElse("None");
        System.out.println(pmName);
	}
	@Test
	
	public void OptionalFilterTest() {
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister("Narendra Modi"));
        //Using Optional.isPresent
        System.out.println(pm.isPresent());
        
	}
	@Test
	public void Predicatetest(){
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister("Narendra Modi"));
        //Using Optional.filter
        Predicate<PrimeMinister> pmPredicate = (p)-> p.getName().length() > 15;
        System.out.println(pm.filter(pmPredicate));
		
	}
	@Test
	public void Predicatetest2(){
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister("Narendra Modi"));
        //Using Optional.ifPresent
        Consumer<PrimeMinister> pmConsumer = (PrimeMinister p) -> System.out.println(p.getName());
        pm.ifPresent(pmConsumer);
	}
	//In the example we have created two Predicate and then creating a student object, we are passing it to test method of Predicate. 
	@Test
	public void studenttest() {
		Predicate<Student1> maleStudent = s-> s.age >= 20 && "male".equals(s.gender);
	      Predicate<Student1> femaleStudent = s-> s.age > 15 && "female".equals(s.gender);
	      
	      Function<Student1,String> maleStyle = s-> "Hi, You are male and age "+s.age;
	      
	      Function<Student1,String> femaleStyle = s-> "Hi, You are female and age "+ s.age;
	      
	      Student1 s1 = new Student1(21,"male");
	      if(maleStudent.test(s1)){
	          System.out.println(s1.customShow(maleStyle));
	      }else if(femaleStudent.test(s1)){
	          System.out.println(s1.customShow(femaleStyle));
	      }      
	  }    
		
	@Test
	public void comparingComparotorTest() {
		String[] stringsArray = "The quick brown fox has a dirty ladder".split("\\s+");
		System.out.println(
                Arrays.stream(stringsArray)
              .sorted(Comparator.<String,String>comparing(String::toLowerCase).reversed())
                .collect(Collectors.toList())
        );
    }
	@Test
	public void TestingSummingoperationsonList() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
		//final IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(Integer::intValue).summaryStatistics();
        //System.out.println("intSummaryStatistics = " + intSummaryStatistics);
		//sum it, flavour 1 There's an extra level of boxing going on in as the reduction function is a BinaryOperator<Integer> - it gets passed two Integer values, unboxes them, adds them, and then re-boxes the result
	    int sum1 = list.stream().reduce(0, (acc, e) -> acc + e).intValue();

	    //sum it, flavour 2 he mapToInt version unboxes the Integer elements from the list once and then works with primitive int values from that point on as an IntStream.
	    int sum2 = list.stream().mapToInt(e -> e).sum();
	    List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
	    //IntSummaryStatistics. It is this object which provides us utility method like getMin(), getMax(), getSum() or getAverage()

//Read more: http://java67.blogspot.com/2014/04/java-8-stream-api-examples-filter-map.html#ixzz3wUz9eEcM
	    IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
	    		 System.out.println(stats + "ffffffffffffffffffff " + stats);
	    		//Read more: http://java67.blogspot.com/2014/04/java-8-stream-api-examples-filter-map.html#ixzz3wUxwLMWV
	    System.out.println(sum1 + "ffffffffffffffffffff " + sum2);
		
	}
	
    
}
