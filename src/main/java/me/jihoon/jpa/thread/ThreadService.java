package me.jihoon.jpa.thread;

import java.util.List;
import me.jihoon.jpa.channel.Channel;

public interface ThreadService {

  List<Thread> selectNotEmptyThreadList(Channel channel);

  Thread insert(Thread thread);
}
