# [겨울인턴] 2023년 퍼플아이오 백엔드 테스트

## 구조

### 개발 환경
- Language : `Java 17`
- Framework : `Spring Boot 2.7.8`

### 사용 라이브러리
- Thymeleaf + jQuery
- Lombok
- Json Simple

### 요청 흐름

1. 클라이언트에게 `View`에서 `URL`을 입력 받습니다.
2. `Ajax`를 통한 `GET` 요청으로 `RestController`에 `URL`을 전달합니다.
3. 각 플랫폼 마다 `oEmbed`에 대한 요청 `URL`이 다르기 때문에 요청 방식에 맞추고, 클라이언트에게 입력받은 `URL`을 연결해줍니다.
   ```bash
   # Youtube
   https://www.youtube.com/oembed?url=${client_request_url}
   
   # Twitter
   https://publish.twitter.com/oembed?url=${client_request_url}
   
   # Vimeo
   https://vimeo.com/api/oembed.json?url=${client_request_url}
   ```
4. `HttpURLConnection`을 통해 완성된 `URL`에 `GET` 요청을 보내 응답 결과를 받아옵니다.
5. `JSONParser`를 이용해 받아온 결과를 `JSONObject` 형태로 변환합니다.
6. `View`에 반환하여 결과에 맞게 내용을 그려줍니다.

## 실행 결과

| Youtube | Vimeo | Twitter |
|---------|---------|-------|
| ![image](https://user-images.githubusercontent.com/82663161/216811440-f9730436-e546-4483-afcb-7656d1bbc3f3.png) | ![image](https://user-images.githubusercontent.com/82663161/216819798-2bdd205e-fbeb-4850-b68b-afbebdd7e26c.png) | ![image](https://user-images.githubusercontent.com/82663161/216821082-c875ae40-ffe7-4789-8d87-fe7bd7ab8b86.png) |

## 미구현 사항

### Instagram oEmbed

Meta for Developer [공식문서](https://developers.facebook.com/docs/features-reference/oembed-read)에 따르면 `oEmbed` 기능을 사용하기 위해서는 앱 검수 절차가 필요하도록 변경되었습니다.

테스트를 위해 기존 방식으로 구현을 해보았고, 요청을 보내보니 `JSON` 형태가 아닌 해당 페이지 자체를 다시 반환하는 모습을 볼 수 있었으며, `Postman`에서도 동일한 결과를 확인할 수 있었습니다.

![스크린샷 2023-02-05 오후 7 04 52](https://user-images.githubusercontent.com/82663161/216812790-7ee87036-ff8e-4887-bfe9-7eb2f245bed6.png)

<img width="1058" alt="스크린샷 2023-02-05 오후 7 05 24" src="https://user-images.githubusercontent.com/82663161/216812809-5e119bcd-58df-465f-a395-eef22db16475.png">

위와 같은 사유로 `Instagram`의 `oEmbed` 기능은 구현하지 못했습니다.

## 레퍼런스

- [oEmbed](https://oembed.com/#section1)
- [Vimeo Developer](https://developer.vimeo.com/api/oembed/videos)
- [Twitter Developer Platform](https://developer.twitter.com/en/docs/twitter-for-websites/timelines/guides/oembed-api)
- [Meta for Developer](https://developers.facebook.com/docs/instagram/oembed/)
