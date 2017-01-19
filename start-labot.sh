#!/bin/bash

#. ~/setjenv

exec /home/user/groovy-1.6.0/bin/groovy -cp pircbot-1.4.6/pircbot.jar ManualBot.groovy labot | tee -a channel.log
