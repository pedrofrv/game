
package com.infosistema.bayes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Bayes {

	private HashMap<String, Map<String, Long>> wordsByCategory = new HashMap<String,Map<String, Long>>();
	private Map<String, Long> categories = new HashMap<String, Long>();
	
	
	public void train(String phrase, String category) {
		String[] words = phrase.split("[ ]+");
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			
			incrementWord(category, word);
			incrementCategory(category);
		}
	}
	
	public String classify(String phrase) {
		Set<Entry<String, Map<String, Long>>> all = wordsByCategory.entrySet();
		double probs[] = new double[all.size()];
		int i = 0;
		for (Entry<String, Map<String, Long>> entry : all) {
			String category = entry.getKey();
			System.out.println(category);
			probs[i] = text_prob(phrase,category);
			i++;
		}
		
		for (int j = 0; j < probs.length; j++) {
			System.out.println(j+"::"+probs[j]);
		}
		return null;
	}

	private float text_prob(String phrase, String category) {
		//System.out.println("categories.get(category)->"+categories.get(category));
		//System.out.println("getTotalCount()->"+getTotalCount());
		float ccategory = (float)categories.get(category)/getTotalCount();
		System.out.println("ccategory::"+(ccategory));
		float dprob = doc_prob(phrase,category);
		System.out.println("dprob::"+dprob);
		return ccategory*dprob;
	}
	
	private float doc_prob(String phrase, String category) {
		String[] words = phrase.split("[ ]+");
		float all =1;
		for (int i = 0; i < words.length; i++) {
			float word_weighted_average = word_weighted_average(words[i], category);
			if(word_weighted_average!=0) {
				all*=word_weighted_average;
				//System.out.println("all::"+ all);
			}
		}
		System.out.println("final all:"+all);
		return all;
	}
	
	private float word_weighted_average(String word, String category) {
		//System.out.println("Word:"+word+"  category:"+category);
		Long wordc = wordsByCategory.get(category).get(word);
		Long catc = categories.get(category);
		if(wordc==null) {
			wordc = 0l;
		}
		if(catc==null) {
			catc = 0l;
		}
		//System.out.println("wordc::"+wordc);
		//System.out.println("catc::"+catc);
		float basicp =  (float)wordc/catc;
		//System.out.println("basicp::"+basicp);
		long total = countWordAllCategories(word);
		System.out.println("wwa::"+((float)(total*basicp)/total));
		return ((float)(total*basicp)/total);
	}
	
	
	private long countWordAllCategories(String word) {
		long count = 0l;
		Set<Entry<String, Map<String, Long>>> all = wordsByCategory.entrySet();
		for (Entry<String, Map<String, Long>> entry : all) {
			if(entry.getValue().get(word)!=null) {
				count+= entry.getValue().get(word);
			}
		}
		//System.out.println("countWordAllCategories::"+count);
		return count;
	}
	
	private Long getTotalCount() {
		Set<Entry<String, Long>> entrySet = categories.entrySet();
		long sum =0l;
		for (Entry<String, Long> entry : entrySet) {
			sum+=entry.getValue();
		}
		return sum;
	}

	private void incrementCategory(String category) {
		Long value = categories.get(category);
		if(value==null) {
			value = 0l;
		}
		value++;
		categories.put(category, value);
	}

	private void incrementWord(String category, String word) {
		Map<String, Long> pwords = wordsByCategory.get(category);
		if(pwords==null) {
			pwords = new HashMap<String, Long>();
			wordsByCategory.put(category,pwords);
		}
		Long value = pwords.get(word);
		if(value == null) {
			value = 0l;				
		}
		value++;
		pwords.put(word,value);
	}
	
	private void print() {
		Set<Entry<String, Map<String, Long>>> all = wordsByCategory.entrySet();
		for (Entry<String, Map<String, Long>> entry : all) {
			System.out.println(entry.getKey());
			System.out.println("____________________________");
			Set<Entry<String, Long>> words = entry.getValue().entrySet();
			for (Entry<String, Long> entry2 : words) {
				System.out.println(entry2.getKey()+":"+entry2.getValue());
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bayes b = new Bayes();
		b.train("Quero fazer uma transferência", "transferencia");
		b.train("Transferir 100 euros para a conta X", "transferencia");
		b.train("Faz uma transferência de 100 euros para a conta X", "transferencia");
		b.train("Diz-me o saldo","saldo");
		b.train("Qual é o meu saldo","saldo");
		b.train("Qual é o meu dinheiro na conta", "saldo");
		b.print();
		b.classify("100 euros de transferência");

	}

}
