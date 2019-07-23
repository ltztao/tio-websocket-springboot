package com.lt.samples;

import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;
import org.tio.core.intf.GroupListener;

/**
 * @author fyp
 * @crate 2019/6/13 21:25
 * @project tio-starters
 */
@Service
public class SpringBootGroupListener implements GroupListener {
    @Override
    public void onAfterBind(ChannelContext channelContext, String s) throws Exception {

    }

    @Override
    public void onAfterUnbind(ChannelContext channelContext, String s) throws Exception {

    }
}
