package com.treemaswebapi.treemaswebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treemaswebapi.treemaswebapi.entity.ProjectMaster;

public interface ProjectMasterRepo extends JpaRepository<ProjectMaster, String> {
    ProjectMaster findByKodeProject(String kodeProject);
}
