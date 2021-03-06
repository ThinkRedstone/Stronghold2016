import logging
import time
import os.path
from os import getpid
##################
#     Logger     #
##################

loggerFileName = (str(time.ctime()) + ".log").replace(" ", "_")
logging.basicConfig(filename='/var/log/roboticsVision/'+loggerFileName,level=logging.DEBUG)
logger = logging.getLogger()

##################
#   Logger Keys  #
##################

# Current U Width In Pixels:
# Current U Height In Pixels:
# Current U X In Pixels:
# Current U Y In Pixels:
# Distance From Tower:
# Angle From tower:
# X Robot Position:
# Y Robot Position:
# AzimuthalAngle:
# PolarAngle:
# FPS:

#################
# Sections Keys #
#################

#Image Object (U):
#Robot Information:

##################
#    Lock File   #
##################

class LockFile():
    '''
    A class helps handling a lock file.
    1 is locked, 0 is unlocked.
    '''
    def __init__(self,pathToFile):
        '''
        Init the variables of the class.
        :param pathToFile: The path to the lock file
        :return: None
        '''
        self.pathToFile = pathToFile

    def lock(self):
        with open(self.pathToFile,"w") as f:
            f.write(str(os.getpid()))

    def is_locked(self):
        '''
        A method returns if the file is locked.
        :return: A bool value True is locked, False is unlocked.
        '''
        if not os.path.exists(self.pathToFile):
            return False
        with open(self.pathToFile, 'r') as f:
            firstLine = f.readline()
            if firstLine == "":
                return False
            return check_pid(firstLine)

def check_pid(pid):
    """ Check For the existence of a unix pid. """
    try:
        os.kill(int(pid), 0)
    except OSError:
        return False
    else:
        return True