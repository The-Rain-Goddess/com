package com.rain.lexicon;

public enum WordType {
	NOUN(1), PRONOUN(2), ADJECTIVE(3), VERB(4), ADVERB(5), PREPOSITION(6),
	CONJUNCTION(7), INTERJECTION(8), ARTICLE(9);
	
	final int wordType;
	
	WordType(int wordType){
		this.wordType = wordType;
	}

}
