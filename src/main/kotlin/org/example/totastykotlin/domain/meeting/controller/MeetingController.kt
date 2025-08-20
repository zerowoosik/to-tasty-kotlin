package org.example.totastykotlin.domain.meeting.controller

import io.swagger.v3.oas.annotations.Parameter
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/meetings")
class MeetingController : MeetingApi{

    @GetMapping
    override fun getMeetings(
        @RequestParam(required = false) filter: String?,
        @RequestParam(required = false) drinkType: DrinkType?,
        @RequestParam(required = false) sort: SortType?,
        @RequestParam(required = false) memberId: Long?,
        @Parameter(hidden = true) pageable: Pageable?
    ): ResponseEntity<*> {
//        val meetings: Slice<*> =
//
//        val response : SliceResponse<String> = SliceResponse.of(meetings)
        return ResponseEntity.status(HttpStatus.OK).body("test")
    }

}