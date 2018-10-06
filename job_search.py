from bs4 import BeautifulSoup as bs
import requests

theJob = "software+engineer+intern"
url = "https://www.indeed.com/jobs?q=" + theJob + "&l="
page = requests.get(url).text
soup = bs(page, 'lxml')

table = soup.find("table",{"id":"resultsBody"})
table = table.find("table",{"id":"pageContent"})
table = table.find("td",{"id":"resultsCol"})
print(table)

#divList = table.find_all("div",{"class":"row result clickcard"})

for div in divList:
    print(div)
    
print ("0")
