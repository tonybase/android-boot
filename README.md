
# AndBoot

    dagger2 + rxjava + retrofit / MVP
    
# Tools (guava)
    
    Strings
        emptyToNull(String)
        nullToEmpty(String)
        isNullOrEmpty(String)
        ...
        
    Lists
        newArrayList()
        newLinkedList()
        newCopyOnWriteArrayList()
        ...
        
    Sets
        newHashSet()
        newTreeSet()
        newLinkedHashSet()
        newIdentityHashSet()
        newCopyOnWriteArraySet()
        ...
        
    Maps
        newHashMap()
        newLinkedHashMap()
        newConcurrentHashSet()
        newCopyOnWriteArraySet()
        ...
         
    Queues
        newArrayDeque()
        newPriorityQueue()
        newSynchronousQueue()
        newLinkedBlockingDeque()
        newConcurrentLinkedQueue()
        newPriorityBlockingQueue()
        ...
    
    Caches
        Cache<Object, Object> caches = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .removalListener(listener)
            .build();
            
    Files
        copy(File from, File to)
        write(byte[] from, File to)
        newWriter(File file, Charset charset)
        newReader(File file, Charset charset)
        toByteArray(File file)
        toString(File file, Charset charset)
        