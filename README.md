# JRobotX

Library to provide compliance with the Web Robot Exclusion protocol (robots.txt). This is a fork of https://github.com/TrigonicSolutions/jrobotx

## Improvements in this fork compared to the original

* Allow specifying a default value used when the robots.txt is unavailable (slight change to RobotExclusion.get())

* Recognize both User-Agent and User-agent (User-Agent is a usual misspelling of the directive)

## Usage

    import com.trigonic.jrobotx.RobotExclusion;

    // ...
    
    RobotExclusion robotExclusion = new RobotExclusion();
    if (robotExclusion.allows(url, userAgentString)) {
        // do something with url
    }
    
