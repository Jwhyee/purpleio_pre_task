# [겨울인턴] 2023년 퍼플아이오 백엔드 테스트

## 실행 결과

| Youtube | Twitter | Vimeo |
|---------|---------|-------|
| ![image](https://user-images.githubusercontent.com/82663161/216811440-f9730436-e546-4483-afcb-7656d1bbc3f3.png) | ![image](https://user-images.githubusercontent.com/82663161/216811591-7b9636ed-f60f-43f3-819c-928a76e191ec.png) | ![image](https://user-images.githubusercontent.com/82663161/216811416-f14d8646-c0b5-420d-be40-4cbcf43ce5b7.png) |

## 구조

### 개발 환경
- Language : `Java 17`
- Framework : `Spring Boot 2.7.8`

### 사용 라이브러리
- Thymeleaf + jQuery
- Lombok
- Simple Json

### 요청 흐름

1. 클라이언트에게 `URL` 입력을 받습니다.
2. `RestController`를 통해 입력된 값을 `oEmbed`로 받기 위해 각 플랫폼에 형태로 변환합니다.
   - 예) Youtube ➡️ `"https://www.youtube.com/oembed?url=https://youtube.com/watch?v="`
3. 요청을 보낼 영상(페이지)의 `URI`를 가져와 변환한 문자열과 합칩니다.
   - 예) `"https://www.youtube.com/watch?v=t8LQnUSBqe8&ab_channel=Avocado%F0%9F%A5%91forHaerin"`
4. 합쳐진 문자열을 가지고 `HttpURLConnection`을 이용해 `GET` 요청을 보냅니다.
5. 받아온 문자열을 통해 `JSONObject` 타입으로 변환합니다.
6. 변환된 `JSONObject`를 `Ajax`를 통해 받아 `View`에 값을 넣어줍니다.

## 미구현 사항

### Instagram oEmbed

Meta for Developer [공식문서](https://developers.facebook.com/docs/features-reference/oembed-read)에 따르면 oEmbed 기능을 사용하기 위해서는 앱 검수 절차가 필요하도록 변경되었습니다.

테스트를 위해 기존 방식으로 구현을 해보았고, 요청을 보내보니 `JSON` 형태가 아닌 해당 페이지 자체를 다시 반환하는 모습을 볼 수 있었으며, `Postman`에서도 동일한 결과를 확인할 수 있었습니다.

![스크린샷 2023-02-05 오후 7 04 52](https://user-images.githubusercontent.com/82663161/216812790-7ee87036-ff8e-4887-bfe9-7eb2f245bed6.png)

<img width="1058" alt="스크린샷 2023-02-05 오후 7 05 24" src="https://user-images.githubusercontent.com/82663161/216812809-5e119bcd-58df-465f-a395-eef22db16475.png">

위와 같은 사유로 `Instagram`의 `oEmbed` 기능은 구현하지 못했습니다.

## 레퍼런스

- [oEmbed](https://oembed.com/#section1)
- [Vimeo Developer](https://developer.vimeo.com/api/oembed/videos)
- [Twitter Developer Platform](https://developer.twitter.com/en/docs/twitter-for-websites/timelines/guides/oembed-api)
- [Meta for Developer](https://developers.facebook.com/docs/instagram/oembed/)
