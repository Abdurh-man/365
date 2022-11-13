#Abdurhman Bahour
#abahour2
from email.errors import StartBoundaryNotFoundDefect
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
import pylab ,matplotlib.ticker, requests,re, operator
import csv
from collections import Counter

file_url = []
with open('sitesToScrap.csv','r') as csvfile:
    lines = csv.reader(csvfile, delimiter=',')
    for row in lines:     
        file_url.append((row[0],row[1])) 

book_count = 1
unique = []
Every_word = []
for url in file_url:
    r = requests.get(url[0])
    soup = BeautifulSoup(r.text, 'html.parser')               
    Book = soup.text

    wordsInText = re.split('START OF THE PROJECT GUTENBERG EBOOK.*',Book) 
    wordsInText = re.split('END OF THE PROJECT GUTENBERG EBOOK.*',wordsInText[1])  
    word = re.split('\s+',wordsInText[0].lower())
    
    master_list = []
    for i in range(len(word)):
        
        if word[i] == "'" or word == "-":
            master_list.append(word[i])
            continue
      
        wordList = re.split('—', word[i])
        if (len(wordList) == 2):
            for i in range(len(wordList)):
                bad_char = [',','"','.','&','|',':','@',',','<','>','(',')','*','$','?','!','\\','/',';','=','”','“','\[','\]']
        
                wordList[i] = re.sub('[' + re.escape(''.join(bad_char)) + ']', '', wordList[i])
                wordList[i] = re.sub(r'^[0-9\.]*$', '', wordList[i])
            
            master_list.append(wordList[0])
            master_list.append(wordList[1])
            continue
        
        bad_char = [',','"','.','&','|',':','@',',','<','>','(',')','*','$','?','!','\\','/',';','=','”','“','\[','\]']
        
        word[i] = re.sub('[' + re.escape(''.join(bad_char)) + ']', '', word[i])
        #word[i] = re.sub(r'[~^0-9]', '', word[i])
        #word[i] = re.sub('[,".&|:@,<>()*$?!\\/;=]”“\[\]', "", word[i])
        word[i] = re.sub(r'^[0-9\.]*$', '', word[i])

        master_list.append(word[i])

    wordDictionary = Counter(master_list)

    stopwords = ['ut', '\'re','.', ',', '--', '\'s','cf', '?', ')', '(', ':','\'','\"', '-', '}','â','£', '{', '&', '|', u'\u2014', '', ']' ]
    for stopword in stopwords:
        wordDictionary.pop(stopword,None)
    sortedWordFreqency = sorted(wordDictionary.items(), key = operator.itemgetter(1), reverse=True)
    top25Words = sortedWordFreqency[0:25]

    for words in sortedWordFreqency:
        unique.append(words[0])
        temp = []
        temp.append(words[0])
        temp.append(words[1])
        temp.append(book_count)

        Every_word.append((temp))
    book_count+=1

list_of_unique = Counter(unique)

for un in list_of_unique:
    if list_of_unique[un] != 1:
        Every_word = list(filter(lambda x: x[0] != un, Every_word))

offset = 0 
book = 1
exclusive = [0 for i in range(25)]
while(True):
    if Every_word[offset][2] == book:
        f = open("{}.txt".format(file_url[book-1][1]),'w')
        book+=1
    else:
        offset+=1
        continue

    i = 0
    while i < 25:     
        info = Every_word[offset]   
        
        temp = []
        temp.append(info[0])
        temp.append(info[1])
        exclusive[i] = temp
        f.write(    "('{}', {})\n".format( str(info[0]),str(info[1]) )     ) 

        offset+=1
        i+=1
        
    f.close()
    if book == book_count:
        break

fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(12, 5))
ax1.barh([ x [0] for x in top25Words ],[ x [1] for x in top25Words ],color = 'red')
ax1.set_title('Original word frequency')
ax2.barh([ x [0] for x in exclusive ],[ x [1] for x in exclusive ],color = 'blue')
ax2.set_title('Frequency of the unique words')

plt.show()
