package com.mercadolibre.challenge.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GlobalUtilsTest {

	@Test
	public void testCloneNullSafetyList() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("clone");
		list.add("null");
		List<String> clonedList = GlobalUtils.cloneNullSafety(list);
		assertThat(clonedList).hasSize(2).contains("clone", atIndex(0)).contains("null", atIndex(1));

		List<Object> listNull = null;
		List<Object> clonedListNull = GlobalUtils.cloneNullSafety(listNull);
		assertThat(clonedListNull).isNotNull().isEmpty();
	}

	@Test
	public void testNullSafetyList() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("null");
		list.add("safety");
		List<String> nullSafetyList = GlobalUtils.nullSafety(list);
		assertThat(nullSafetyList).hasSize(2).contains("null", atIndex(0)).contains("safety", atIndex(1));

		List<Object> listNull = null;
		List<Object> nullSafetyListNull = GlobalUtils.nullSafety(listNull);
		assertThat(nullSafetyListNull).isNotNull().isEmpty();
	}
}
