package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val testChars = List('y', 'a', 'b', 'a', ' ', 'd', 'a', 'b', 'a')
    val l1 = Leaf('e', 1)
    val l2 = Leaf('t', 2)
    val l3 = Leaf('x', 4)
    val c1 = List(('a', List(0)), ('b', List(1)))
    val c2 = List(('a', List(0,0)), ('b', List(0,1)), ('d', List(1)))
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("Count chars frequency") {
    new TestTrees {
      assert(times(testChars) === List(('y', 1), ('a', 4), (' ', 1), ('b', 2), ('d', 1)))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("Check singleton for list of trees") {
    new TestTrees {
      assert(singleton(List(t1)) === true)
      assert(singleton(List(t1, t2)) === false)
    }
  }


  test("combine of some leaf list") {
    new TestTrees {
      assert(combine(List()) === List())
      assert(combine(List(l1)) === List(l1))
      assert(combine(List(l1, l2, l3)) === List(Fork(l1,l2,List('e', 't'),3), l3))
    }

  }

  test("Combine list until reaching a singleton") {
    new TestTrees {
      assert(until(singleton,combine)(List(l1)) === List(l1))
      assert(until(singleton,combine)(List(l1, l2)) === List(Fork(l1, l2, List('e','t'),3)))
      assert(until(singleton,combine)(List(l1, l2, l3)) === List(Fork(Fork(l1,l2,List('e','t'),3),l3, List('e','t','x'),7)))
    }
  }

  test("Create code tree from list of chars") {
    new TestTrees {
      val charsList = List('t', 'e', 'x', 'x', 't', 'x', 'x')
      assert(createCodeTree(charsList) === Fork(Fork(l1,l2,List('e','t'),3),l3, List('e','t','x'),7))
      assert(createCodeTree(List('t', 'e', 't')) === Fork(l1,l2, List('e','t'),3))
      assert(createCodeTree(List('e')) === l1)
    }

  }

  test("decode short text") {
    new TestTrees {
      assert(decode(t1, List(0,0,1)) === "aab".toList)
      assert(decode(t1, List(0,0,1,0,1,1,0)) === "aababba".toList)
      assert(decode(t2, List(0,0,1)) === "ad".toList)
      assert(decode(t2, List(0,0,1,0,1,1)) === "adbd".toList)

      assert(
        intercept[Exception] {
          decode(t2, List(0, 0, 1, 0, 1, 1, 0))
        }.getMessage === "Bad sequence")
    }
  }

  test("encode short text") {
    new TestTrees {
      assert(encode(t1)("aab".toList) === List(0,0,1))
      assert(encode(t1)("aababba".toList) === List(0,0,1,0,1,1,0))
      assert(encode(t2)("ad".toList) === List(0,0,1))
      assert(encode(t2)("adbd".toList) === List(0,0,1,0,1,1))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
      assert(decode(t1, encode(t1)("aab".toList)) === "aab".toList)
      assert(decode(t1, encode(t1)("aababba".toList)) === "aababba".toList)
      assert(decode(t2, encode(t2)("ad".toList)) === "ad".toList)
      assert(decode(t2, encode(t2)("adbd".toList)) === "adbd".toList)
      assert(decode(t2, encode(t2)("adbdbdd".toList)) === "adbdbdd".toList)
    }
  }

  test("convert tree to code table") {
    new TestTrees {
      assert(convert(t1) == c1)
      assert(convert(t2) == c2)
    }
  }

  test("quickencode short text") {
    new TestTrees {
      assert(quickEncode(t1)("aab".toList) === List(0,0,1))
      assert(quickEncode(t1)("aababba".toList) === List(0,0,1,0,1,1,0))
      assert(quickEncode(t2)("ad".toList) === List(0,0,1))
      assert(quickEncode(t2)("adbd".toList) === List(0,0,1,0,1,1))
    }
  }

}
