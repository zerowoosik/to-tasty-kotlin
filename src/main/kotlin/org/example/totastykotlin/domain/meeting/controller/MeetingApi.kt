package org.example.totastykotlin.domain.meeting.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.totastykotlin.domain.auth.UserDetailsImpl
import org.example.totastykotlin.domain.common.dto.response.SliceResponse
import org.example.totastykotlin.domain.meeting.dto.response.MeetingListResponse
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "모임 관련 API", description = "모임 생성, 조회, 수정, 삭제, 참가 관련 API")
interface MeetingApi {

    @Operation(
        summary = "모임 리스트 조회 (필터/정렬)",
        description = "필터링과 정렬 옵션을 적용하여 모임 리스트를 무한스크롤 방식으로 조회합니다.",
        parameters = [Parameter(
            name = "filter",
            description = "지역(시/도) 필터",
            example = "서울"
        ), Parameter(name = "drinkType", description = "음료 타입 필터", example = "WINE"), Parameter(
            name = "sort",
            description = "정렬 방식",
            example = "latest"
        ), Parameter(
            name = "memberId",
            description = "특정 회원이 참여한 모임만 조회 (선택사항)",
            example = "1"
        ), Parameter(
            name = "page",
            description = "페이지 번호 (1부터 시작)",
            example = "1",
            `in` = ParameterIn.QUERY
        ), Parameter(name = "size", description = "페이지 크기", example = "20", `in` = ParameterIn.QUERY)]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "모임 리스트 조회 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(
                        implementation = SliceResponse::class
                    ),
                    examples = [ExampleObject(
                        value = """
                        {
                          "content": [
                            {
                              "meetingId": 101,
                              "meetingAuthor": "홍길동",
                              "meetingTitle": "우리 오늘 취해봐요~ 낭만에 취해요",
                              "location": {
                                "sido": "서울",
                                "address": "을지로 34-2",
                                "detail": "3층 카페"
                              },
                              "participationFee": 30000,
                              "startAt": "2025-11-20T10:30:00",
                              "joinEndAt": "2025-09-19T23:59:00",
                              "maxParticipants": 5,
                              "minParticipants": 3,
                              "currentParticipants": 2,
                              "tastingDrinkCount": 3,
                              "isWished": true,
                              "thumbnailUrl": "https://example.com/image.jpg",
                              "status": "open",
                              "drinkType": "WINE",
                              "isReviewed": false,
                              "participation": [
                                {
                                  "memberId": 1,
                                  "nickname": "홍길동",
                                  "profileImgUrl": "https://example.com/profile1.jpg",
                                  "isHost": true
                                },
                                {
                                  "memberId": 2,
                                  "nickname": "김철수",
                                  "profileImgUrl": "https://example.com/profile2.jpg",
                                  "isHost": false
                                }
                              ]
                            }
                          ],
                          "sliceInfo": {
                            "currentPage": 1,
                            "size": 20,
                            "hasNext": true
                          }
                        }
                    """
                    )],
                )]
            ),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun getMeetings(
        @RequestParam(required = false) filter: String? = null,
        @RequestParam(required = false) drinkType: DrinkType? = null,
        @RequestParam(required = false) sort: SortType? = null,
        @RequestParam(required = false) memberId: Long? = null,
        @Parameter(hidden = true) pageable: Pageable? = null,
        @AuthenticationPrincipal userDetails: UserDetailsImpl
    ): ResponseEntity<SliceResponse<MeetingListResponse>>

}