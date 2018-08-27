# interview

//. From 1point 3acres bbs
// Design and implement key value system
//
// string -> string

// Insert a key-value pair or modify a value. 1point 3acres 
void put(string key, string value);

// Delete a key-value pair. 1point 3acres 
void delete(string key);

// Gets a value given a snapshot. 
string get(string key, Snapshot snap);

// Take a snapshot. 
Snapshot takeSnapshot();

// Delete a snapshot
void deleteSnapshot(Snapshot snap);

// put(k1,v1)
// put(k2,v2)
// put(k3,v3)
// takeSnapshot -> snap1 { k1 -> v1, k2 -> v2, k3 -> v3 }. 
// get(k1,snap1) -> v1
// put(k1,v4)
// delete(k3). more info on 1point3acres
// takeSnapshot -> snap2 { k1 -> v4, k2 -> v2 }
// get(k1,snap2) -> v4
// get(k1,snap1) -> v1. visit 1point3acres for more.
// get(k3,snap2) -> XX.1point3acres网
// deleteSnapshot(snap1)
// get(k1,snap1) -> XX

// Space efficient > time efficient

key -> value
key    {snap_id, _new_value}
*/