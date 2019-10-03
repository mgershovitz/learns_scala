package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)

    val u1 = union(s1, s2)
    val u2 = union(s3, s2)
    val u3 = union(s3, s4)
    val all = union(u1, u3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("Intersection only contains elements from both sets") {
    new TestSets {
      val i = intersect(u1, u2)

      assert(contains(i, 2), "Intersect 2")
      assert(!contains(i, 1), "Intersect 1")
      assert(!contains(i, 3), "Intersect 3")
    }
  }

  test("diff only contains elements from the first set, and not from the second one") {
    new TestSets {
      val d = diff(u1, u2)

      assert(contains(d, 1), "Diff 1")
      assert(!contains(d, 2), "Diff 2")
      assert(!contains(d, 3), "Diff 3")
    }
  }

  test("test forall") {
    new TestSets {
      assert(forall(s1, (x:Int) => x==1))
    }
  }


  test("test filter function") {
    new TestSets {
      val emptySet = filter(s1, (x: Int) => x > 1)
      assert(!contains(emptySet, 1), "Set 1 after filter x > 1 doesn't contain 1")
    }

    new TestSets {
      val filteredSet = filter(u1, (x: Int) => x > 1)
      assert(!contains(filteredSet, 1), "Union 1,2 after filter x > 1 doesn't contain 1")
      assert(contains(filteredSet, 2), "Union 1,2 after filter x > 1 contains 2")
    }


      new TestSets {
        val unFilteredSet = filter(u1, (x: Int) => x > 0)
        assert(contains(unFilteredSet, 1), "Union 1,2 after filter x > 0 contains 1")
        assert(contains(unFilteredSet, 2), "Union 1,2 after filter x > 0 contains 2")
      }

  }

  test("test mapping function") {
    new TestSets {
      val m1 = map(s1, (x: Int) => x + 1)

      assert(!contains(m1, 1), "s1 increment doesn't contain 1")
      assert(contains(m1, 2), "s1 increment contains 2")
    }

    new TestSets {
      val m2 = map(u1, (x: Int) => 2)
      assert(!contains(m2, 1), "all items in u1 mapped to 2, doesn't contain 1")
      assert(contains(m2, 2), "all items in u1 mapped to 2, contains 1")
    }

    new TestSets {
      val m3 = map(all, (x: Int) => 2*x)

      assert(forall(m3, (x: Int) => x%2==0))

    }


  }


}
