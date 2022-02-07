private String apiKey = "<---Your_Key--->";

    public News getTopStories() {
     List<News> topStories = new ArrayList<>();

    String getUrl = "https://api.time.com/svc/topstories/v2/science.json?api-key=" + apiKey;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<Map> newsList = restTemplate.exchange(getUrl, HttpMethod.GET, entity, Map.class);
    JSONObject jsonObject;

    if (newsList.getStatusCode() == HttpStatus.OK) {

        jsonObject = new JSONObject(newsList.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        Results[] results = new Results[jsonArray.length()];

        for(int i=0; i<jsonArray.length(); i++) {
            News news = new News();
            news.setTitle(jsonArray.getJSONObject(i).get("title").toString());
            news.setSection(jsonArray.getJSONObject(i).get("section").toString());
            String title=jsonArray.getJSONObject(i).get("title").toString();
            results[i]=new Results();
            results[i].setTitle(title);
            news.setResults(results);
            topStories.add(news);
        }
    }
    return topStories.get(0);
  }