package org.example.totastykotlin.domain.meeting.controller

import io.swagger.v3.oas.annotations.Parameter
import org.example.totastykotlin.domain.auth.UserDetailsImpl
import org.example.totastykotlin.domain.common.dto.response.SliceResponse
import org.example.totastykotlin.domain.meeting.dto.response.MeetingListResponse
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.domain.meeting.service.MeetingService
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/meetings")
class MeetingController(
    val meetingService: MeetingService,
) : MeetingApi{

    @GetMapping
    override fun getMeetings(
        @RequestParam(required = false) filter: String?,
        @RequestParam(required = false) drinkType: DrinkType?,
        @RequestParam(required = false) sort: SortType?,
        @RequestParam(required = false) memberId: Long?,
        @Parameter(hidden = true) pageable: Pageable?,
        @AuthenticationPrincipal userDetails: UserDetailsImpl,
    ): ResponseEntity<SliceResponse<MeetingListResponse>> {
        val meetings: Slice<MeetingListResponse>

//        if(memberId != null) {
//            meetings = if(userDetails != null) {  } else{ }
//        }

        meetings = meetingService.getMeetings(filter, drinkType, sort, pageable)
        val response: SliceResponse<MeetingListResponse> = SliceResponse.of(meetings)
        return ResponseEntity.ok(response)
    }

}