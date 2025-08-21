package org.example.totastykotlin.domain.auth

import org.example.totastykotlin.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    val memberId: Long? = null,
    val nickname: String? = null,
) : UserDetails {

    companion object {
        fun of(member: Member): UserDetailsImpl {
            return UserDetailsImpl(memberId = member.id, nickname = member.nickname)
        }
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return emptyList()
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String? {
        return this.nickname
    }
}