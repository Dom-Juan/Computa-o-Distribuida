# -*- coding: utf-8 -*-

from distutils import command
import multiprocessing as mp
from multiprocessing.dummy import current_process
import os, platform, subprocess, re, secrets

from multiprocessing import Process, Queue, current_process

def info(title):
  print(title)
  print('module name: ', __name__)
  print('parent process:', os.getppid())
  print('process id:', os.getpid())

def show_processor():
  if(platform.system() == "Windows"):
    print(platform.processor())
  elif(platform.systems() == "Darwin"):
    os.environ['PATH'] = os.environ['PATH'] + os.pathsep + '/usr/sbin'
  else:
    print("")

def calculate(q):
  name = current_process().name
  number = q.get()
  print(f'nome: {name} recebeu: {number} pid: {os.getpid()}')
  print("Quadrado calculado: ", number*number)

def create_queue():
  q = Queue()
  for i in range(20):
    number = secrets.randbelow(i + 1)
    #print(number)
    q.put(number)
  return q
    
  
def main(args):
  q = create_queue()
  process = []
  for i in range(20):
    process.append(Process(target=calculate, args=(q,)))
  
  for p in process:
    p.start()
    #p.join()
  
  for p in process:
    p.join()
  
  return 0
  
if __name__ == '__main__':
  info('Main pipe')
  show_processor()
  main(None)
  print(f'Terminado: {__name__}')
  