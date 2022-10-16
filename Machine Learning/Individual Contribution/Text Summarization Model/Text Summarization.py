from configparser import MAX_INTERPOLATION_DEPTH
from heapq import nlargest
import spacy
from spacy.lang.en.stop_words import STOP_WORDS
from string import punctuation

stop_words = list(STOP_WORDS)
punctuation += '\n'
nlp = spacy.load('en_core_web_sm')

def summarizer(input_text):
    doc = nlp(input_text)
    word_frequencies = {}
    for word in doc:
        if word.text.lower() not in stop_words:
            if word.text.lower() not in punctuation:
                if word.text not in word_frequencies.keys():
                    word_frequencies[word.text] = 1
                else:
                    word_frequencies[word.text] += 1
    
    max_frequency = max(word_frequencies.values())
    for word in word_frequencies.keys():
        word_frequencies[word] = word_frequencies[word]/max_frequency
    
    sentence_tokens = [sent for sent in doc.sents]
    sentence_scores = {}

    for sent in sentence_tokens:
        for word in sent:
            if word.text.lower() in word_frequencies.keys():
                if sent not in sentence_scores.keys():
                    sentence_scores[sent] = word_frequencies[word.text.lower()]
                else:
                    sentence_scores[sent] += word_frequencies[word.text.lower()]
    
    select_length = int(len(sentence_tokens)*0.3)
    summary_list = nlargest(select_length,sentence_scores,key=sentence_scores.get)
    final_summary = [word.text for word in summary_list]
    summary = ' '.join(final_summary)
    return summary