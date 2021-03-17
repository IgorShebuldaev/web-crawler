# Web-crawler
A simple search engine that analyzes pages by keywords. Collects statistics for each page and exports this information to a csv file.
## Requirements
Docker
## Getting Started
    docker pull wannaasbird/webcrawler:java 
    docker run -v $(pwd)/result:/output wannaasbird/webcrawler:java args
After that, the results folder will be created in the root of the project, where the result of the parser will be located.
## Specification for command-line arguments
    -l link
    -p page limit  
    -d depth
    -t terms
By default, the search limit is 10,000 pages, and the depth is 8 from the start link, so the page limit or depth parameters are optional, but you can also specify 1 of them.
## Roadmap
Be careful, with a page limit of 10,000, the program collects statistics for about an hour, so at the moment the highest priority is to implement multithreading.