package me.jihoon.jpa.userchannel;

import jakarta.transaction.Transactional;
import me.jihoon.jpa.channel.Channel;
import me.jihoon.jpa.channel.ChannelRepository;
import me.jihoon.jpa.user.User;
import me.jihoon.jpa.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserChannelRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void userJoinChannelTest() {
        // given
        var newChannel = Channel.builder().name("new-channel").build();
        var newUser = User.builder().username("new_user").password("new-pass").build();
        var newUserChannel = newChannel.joinUser(newUser);

        // when
        var savedChannel = channelRepository.insertChannel(newChannel);
        var savedUser = userRepository.insertUser(newUser);

        // then
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        assert foundChannel.getUserChannels().stream()
                .map(UserChannel::getChannel)
                .map(Channel::getName)
                .anyMatch(name -> name.equals(newChannel.getName()));
    }

    @Test
    void userJoinChannelWithCascadeTest() {
        // given
        var newChannel = Channel.builder().name("new-channel").build();
        var newUser = User.builder().username("new_user").password("new-pass").build();
        newChannel.joinUser(newUser);

        // when
        var savedChannel = channelRepository.insertChannel(newChannel);
        var savedUser = userRepository.insertUser(newUser);

        // then
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        assert foundChannel.getUserChannels().stream()
                .map(UserChannel::getChannel)
                .map(Channel::getName)
                .anyMatch(name -> name.equals(newChannel.getName()));
    }
}
