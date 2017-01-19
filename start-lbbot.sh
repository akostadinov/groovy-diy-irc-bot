#!/bin/bash

exec /qa/tools/opt/groovy-1.5.4/bin/groovy -cp pircbot-1.4.6/pircbot.jar ManualBot.groovy lbbot | tee -a channel-lbbot.log
